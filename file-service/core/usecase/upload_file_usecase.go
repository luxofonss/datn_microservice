package usecase

import (
	"context"
	"fmt"
	"path/filepath"
	"strings"
	"time"

	uploadprovider "file-service/adapter/services/file_upload_provider"
	"file-service/core/entities"
	"github.com/google/uuid"
)

type CreateFileRepo interface {
	CreateResource(ctx context.Context, data entities.Resource) (entities.Resource, error)
}

type uploadFileUseCase struct {
	provider uploadprovider.Provider
	repo     CreateFileRepo
}

func NewUploadFileUseCase(provider uploadprovider.Provider, repo CreateFileRepo) *uploadFileUseCase {
	return &uploadFileUseCase{
		provider: provider,
		repo:     repo,
	}
}

func (useCase *uploadFileUseCase) UploadFile(ctx context.Context, data []byte, folder, fileName string) (*entities.Resource, error) {
	if strings.TrimSpace(folder) == "" {
		folder = "files"
	}

	fileExt := filepath.Ext(fileName)
	fileName = fmt.Sprintf("%d%s", time.Now().Nanosecond(), fileExt)

	file, err := useCase.provider.SaveFileUpload(ctx, data, fmt.Sprintf("%s/%s", folder, fileName))
	if err != nil {
		return nil, err
	}

	file.Type = fileExt
	file.StoreProvider = useCase.provider.GetCloudName()
	file.Name = fileName
	file.Id = uuid.New()

	result, err := useCase.repo.CreateResource(ctx, *file)
	if err != nil {
		return nil, err
	}

	return &result, nil
}
