package repository

import (
	"file-service/adapter/database/entities"
	"file-service/core/common"
	domainEntities "file-service/core/entities"
)

func (repo *resourceRepo) GetResourceByID(id string) (*domainEntities.Resource, error) {
	db := repo.db.Table(entities.ResourceData{}.TableName())

	var resource *entities.ResourceData

	if err := db.Where("id = ?", id).First(&resource).Error; err != nil {
		return nil, common.ErrDB(err)
	}

	var result = resource.ToResource()

	return &result, nil
}
