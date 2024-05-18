package entities

import (
	"time"

	"file-service/core/common"
	"github.com/google/uuid"
)

type Resource struct {
	Id            uuid.UUID  `json:"id"`
	Name          string     `json:"name"`
	StoreProvider string     `json:"storeProvider"`
	Type          string     `json:"type"`
	Url           string     `json:"url"`
	VideoDuration int        `json:"videoDuration"`
	Resolution    string     `json:"resolution"`
	CreatedAt     *time.Time `json:"createdAt"`
	UpdatedAt     *time.Time `json:"updatedAt"`
	DeletedAt     *time.Time `json:"deletedAt"`
}

func ErrCannotSaveFile(err error) *common.AppError {
	return common.NewCustomError(err, "cannot save file", "ErrCannotSaveFile")
}
