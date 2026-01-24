package router

import (
	"api-gateway/internal/config"

	"github.com/gin-gonic/gin"
)

func SetupRouter(cfg *config.Config) *gin.Engine {
	r := gin.Default()

	registerPublicRoutes(r, cfg)
	registerProtectedRoutes(r, cfg)

	return r
}
