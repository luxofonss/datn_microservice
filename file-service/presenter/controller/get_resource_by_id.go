package controller

import (
	"net/http"

	"file-service/adapter/database/repository"
	"file-service/core/common"
	"file-service/core/usecase"
	"file-service/presenter/libs"
	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
)

func GetResourceById(appCtx libs.AppContext) func(ctx *gin.Context) {
	return func(c *gin.Context) {
		resourceId, err := uuid.Parse(c.Param("id"))
		if err != nil {
			c.JSON(http.StatusBadRequest, gin.H{
				"error": err.Error(),
			})
		}

		db := appCtx.GetMainSQLDbConnection()
		repo := repository.NewResourceRepo(db)
		biz := usecase.NewGetFileByIdUseCase(repo)

		data, err := biz.GetResourceById(c.Request.Context(), resourceId.String())
		if err != nil {
			panic(err)
		}

		c.JSON(http.StatusOK, common.SimpleSuccessResponse(data))
	}
}
