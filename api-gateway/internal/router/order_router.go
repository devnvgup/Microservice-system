package router

import (
	"api-gateway/internal/config"
	"api-gateway/internal/middleware"
	"api-gateway/internal/proxy"

	"github.com/gin-gonic/gin"
)

func registerProtectedRoutes(r *gin.Engine, cfg *config.Config) {
	protected := r.Group("/")
	protected.Use(middleware.JWTMiddleware())

	registerOrderRoutes(protected, cfg)
}

func registerOrderRoutes(r *gin.RouterGroup, cfg *config.Config) {
	orders := r.Group("/orders")
	proxy := proxy.NewReverseProxy(cfg.OrderServiceURL)

	orders.Any("", proxy)
	orders.Any("/*path", proxy)
}
