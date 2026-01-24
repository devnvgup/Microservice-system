package proxy

import (
	"api-gateway/internal/config"

	"github.com/gin-gonic/gin"
)

func OrderProxy(cfg *config.Config) gin.HandlerFunc {
	return NewReverseProxy(cfg.OrderServiceURL)
}
