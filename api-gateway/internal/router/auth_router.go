package router

import (
	"api-gateway/internal/config"
	"api-gateway/internal/proxy"

	"github.com/gin-gonic/gin"
)

func registerPublicRoutes(r *gin.Engine, cfg *config.Config) {
	auth := r.Group("/auth")
	proxy := proxy.NewReverseProxy(cfg.AuthServiceURL)

	auth.Any("", proxy)
	auth.Any("/*path", proxy)
}
