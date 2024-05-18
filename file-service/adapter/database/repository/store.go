package repository

import (
	"gorm.io/gorm"
)

type resourceRepo struct {
	db *gorm.DB
}

func NewResourceRepo(db *gorm.DB) *resourceRepo {
	return &resourceRepo{db: db}
}
