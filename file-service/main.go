package main

import (
	"fmt"
	"os"
	"time"

	"file-service/adapter/database"
	uploadprovider "file-service/adapter/services/file_upload_provider"
	"file-service/presenter/libs"
	"file-service/presenter/webserver"
	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
	"gorm.io/gorm"
)

func main() {
	configPostgres := database.DBConfig{
		IdentificationName: "postgres",
		DB:                 "datn",
		User:               "postgres",
		Password:           "admin",
		Host:               "localhost",
		Port:               "5432",
		Type:               "postgres",
		SSLMode:            "disable",
		TimeZone:           "Asia/Ho_Chi_Minh",
	}

	postgresConf := database.NewDatabase(configPostgres.Type)
	postgresDBImpl, err := postgresConf.Database.Connect(configPostgres)
	if err != nil {
		fmt.Println("err postgres type")
		panic(err)
	}

	postgresDb, ok := postgresDBImpl.(*gorm.DB)
	if !ok {
		fmt.Println("err postgres type")
	}

	postgresDb.Debug()

	s3BucketName := os.Getenv("S3_BUCKET_NAME")
	s3Region := os.Getenv("S3_REGION")
	s3APIKey := os.Getenv("S3_API_KEY")
	s3SecretKey := os.Getenv("S3_SECRET_KEY")
	s3Domain := os.Getenv("S3_DOMAIN")

	uploadProvider := uploadprovider.NewS3Provider(s3BucketName, s3Region, s3APIKey, s3SecretKey, s3Domain)

	ginRouter := gin.Default()

	ginRouter.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"http://localhost:3000"},
		AllowMethods:     []string{"POST", "GET", "PUT", "DELETE", "PATCH", "OPTIONS"},
		AllowHeaders:     []string{"Origin", "Content-Type", "Content-Length", "Accept-Encoding", "X-CSRF-Token", "Authorization", "accept", "origin", "Cache-Control", "X-Requested-With", "Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Access-Control-Allow-Methods", "Access-Control-Allow-Credentials"},
		AllowCredentials: true,
		MaxAge:           12 * time.Hour,
	}))

	appContext := libs.NewAppContext(postgresDb, uploadProvider)
	//ginRouter.Use()
	routeGroup := ginRouter.Group("/files")
	webserver.SetupRoutes(appContext, routeGroup)

	ginRouter.Run(":8083") // listen and serve on 0.0.0.0:8083
}
