package webserver

import (
	"net/http"

	"file-service/presenter/controller"
	"file-service/presenter/libs"
	"github.com/gin-gonic/gin"
)

func SetupRoutes(ctx libs.AppContext, r *gin.RouterGroup) {
	r.GET("/ping", func(c *gin.Context) {
		c.JSON(http.StatusOK, gin.H{
			"message": "pong",
		})
	})

	r.GET("/:id", controller.GetResourceById(ctx))
	r.POST("/upload", controller.Upload(ctx))
}
