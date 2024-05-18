package uploadprovider

import (
	"context"

	"file-service/core/entities"
)

type Provider interface {
	SaveFileUpload(ctx context.Context, data []byte, dst string) (*entities.Resource, error)
	GetCloudName() string
}
