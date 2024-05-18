package controller

import (
	"net/http"

	"file-service/adapter/database/repository"
	"file-service/core/common"
	"file-service/core/usecase"
	"file-service/presenter/libs"
	"github.com/gin-gonic/gin"
)

func Upload(appCtx libs.AppContext) func(ctx *gin.Context) {
	return func(c *gin.Context) {
		fileHeader, err := c.FormFile("file")
		if err != nil {
			panic(common.ErrInvalidRequest(err))
		}

		folder := c.DefaultPostForm("folder", "files")

		file, err := fileHeader.Open()
		if err != nil {
			panic(common.ErrInvalidRequest(err))
		}

		defer file.Close()

		dataBytes := make([]byte, fileHeader.Size)
		if _, err := file.Read(dataBytes); err != nil {
			panic(common.ErrInvalidRequest(err))
		}

		db := appCtx.GetMainSQLDbConnection()
		repo := repository.NewResourceRepo(db)
		biz := usecase.NewUploadFileUseCase(appCtx.GetUploadProvider(), repo)
		img, err := biz.UploadFile(c.Request.Context(), dataBytes, folder, fileHeader.Filename)
		if err != nil {
			panic(err)
		}

		c.JSON(http.StatusOK, common.SimpleSuccessResponse(img))
	}
}
