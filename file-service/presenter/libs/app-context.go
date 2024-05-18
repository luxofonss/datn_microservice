package libs

import (
	uploadprovider "file-service/adapter/services/file_upload_provider"
	"gorm.io/gorm"
)

type AppContext interface {
	GetMainSQLDbConnection() *gorm.DB

	GetSecretKey() string

	GetUploadProvider() uploadprovider.Provider
}

type appCtx struct {
	sqlDb          *gorm.DB
	secretKey      string
	uploadProvider uploadprovider.Provider
}

func NewAppContext(
	sqlDb *gorm.DB,
	uploadProvider uploadprovider.Provider,
) *appCtx {
	return &appCtx{
		sqlDb:          sqlDb,
		uploadProvider: uploadProvider,
	}
}

func (ctx *appCtx) GetSecretKey() string {
	return ctx.secretKey
}

func (ctx *appCtx) GetMainSQLDbConnection() *gorm.DB {
	return ctx.sqlDb
}

func (ctx *appCtx) GetUploadProvider() uploadprovider.Provider {
	return ctx.uploadProvider
}
