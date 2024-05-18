package common

import (
	"time"

	"github.com/google/uuid"
)

type SQLModel struct {
	Id        uuid.UUID  `json:"id" gorm:"primaryKey;unique;column:id;type:uuid;default:uuid_generate_v4()"`
	DeletedAt *time.Time `json:"deletedAt" gorm:"column:deleted_at;default:NULL"`
	CreatedAt *time.Time `json:"createdAt" gorm:"column:created_at;default:CURRENT_TIMESTAMP()"`
	UpdatedAt *time.Time `json:"updatedAt" gorm:"column:updated_at;default:CURRENT_TIMESTAMP()"`
}
