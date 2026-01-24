package proxy

import (
	"api-gateway/internal/config"

	"github.com/gin-gonic/gin"
)

func AuthProxy(cfg *config.Config) gin.HandlerFunc {
	return NewReverseProxy(cfg.AuthServiceURL)
}
