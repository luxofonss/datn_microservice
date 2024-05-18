package repository

import (
	"context"

	"file-service/adapter/database/entities"
	"file-service/core/common"
	domainEntities "file-service/core/entities"
)

func (repo *resourceRepo) CreateResource(ctx context.Context, data domainEntities.Resource) (domainEntities.Resource, error) {
	db := repo.db.Table(entities.ResourceData{}.TableName())
	if err := db.Create(&data).Error; err != nil {
		return domainEntities.Resource{}, common.ErrDB(err)
	}

	return data, nil
}
