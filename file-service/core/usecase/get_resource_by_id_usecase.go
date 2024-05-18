package usecase

import (
	"context"

	"file-service/core/entities"
)

type GetResourceByIDRepo interface {
	GetResourceByID(id string) (*entities.Resource, error)
}

type getResourceByIdUseCase struct {
	repo GetResourceByIDRepo
}

func NewGetFileByIdUseCase(repo GetResourceByIDRepo) *getResourceByIdUseCase {
	return &getResourceByIdUseCase{repo: repo}
}

func (useCase *getResourceByIdUseCase) GetResourceById(ctx context.Context, id string) (*entities.Resource, error) {
	result, err := useCase.repo.GetResourceByID(id)
	if err != nil {
		return nil, err
	}

	return result, nil
}
