// internal/router/router.go
package router

import (
	"auth-service/internal/handler"

	"github.com/gin-gonic/gin"
)

func SetupRouter(authHandler *handler.AuthHandler) *gin.Engine {
	r := gin.Default()

	r.POST("/auth/login", authHandler.Login)
	r.POST("/auth/register", authHandler.Register)

	// protected := r.Group("/api")
	// protected.Use(middleware.JWTAuth())
	// {
	// 	protected.GET("/profile", func(c *gin.Context) {
	// 		c.JSON(200, gin.H{"msg": "ok"})
	// 	})
	// }

	return r
}
