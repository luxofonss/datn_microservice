package entities

import (
	"database/sql/driver"
	"encoding/json"
	"errors"
	"fmt"

	"file-service/core/common"
	domainEntities "file-service/core/entities"
)

type ResourceData struct {
	common.SQLModel `json:",inline"`
	Name            string `json:"name" gorm:"column:name;"`
	StoreProvider   string `json:"storeProvider" gorm:"column:store_provider"`
	Type            string `json:"type" gorm:"column:type"`
	Url             string `json:"url" gorm:"column:url;"`
	VideoDuration   int    `json:"videoDuration" gorm:"column:duration;"`
	Resolution      string `json:"resolution" gorm:"column:resolution;"`
}

func (data *ResourceData) ToResource() domainEntities.Resource {
	return domainEntities.Resource{
		Id:            data.Id,
		Name:          data.Name,
		StoreProvider: data.StoreProvider,
		Type:          data.Type,
		Url:           data.Url,
		VideoDuration: data.VideoDuration,
		Resolution:    data.Resolution,
		CreatedAt:     data.CreatedAt,
		UpdatedAt:     data.UpdatedAt,
		DeletedAt:     data.DeletedAt,
	}
}

func ToResourceData(resource domainEntities.Resource) ResourceData {
	return ResourceData{
		SQLModel: common.SQLModel{
			Id:        resource.Id,
			DeletedAt: resource.DeletedAt,
			CreatedAt: resource.CreatedAt,
			UpdatedAt: resource.UpdatedAt,
		},
		Name:          resource.Name,
		StoreProvider: resource.StoreProvider,
		Type:          resource.Type,
		Url:           resource.Url,
		VideoDuration: resource.VideoDuration,
		Resolution:    resource.Resolution,
	}
}

func (ResourceData) TableName() string { return "resources" }

func (j *ResourceData) Scan(value interface{}) error {
	bytes, ok := value.([]byte)

	if !ok {
		return errors.New(fmt.Sprint("Failed to unmarshal JSONB value:", value))
	}

	var resource ResourceData

	if err := json.Unmarshal(bytes, &resource); err != nil {
		return err
	}

	*j = resource
	return nil
}

// Value return json value, implement driver.Value interface
func (j *ResourceData) Value() (driver.Value, error) {
	if j == nil {
		return nil, nil
	}

	return json.Marshal(j)
}

type Resource []ResourceData

func (j *Resource) Scan(value interface{}) error {
	bytes, ok := value.([]byte)

	if !ok {
		return errors.New(fmt.Sprint("Failed to unmarshal JSONB value:", value))
	}

	var resource Resource

	if err := json.Unmarshal(bytes, &resource); err != nil {
		return err
	}

	*j = resource

	return nil
}

func (j *Resource) Value() (driver.Value, error) {
	if j == nil {
		return nil, nil
	}

	return json.Marshal(j)
}
