package middleware

import (
	"api-gateway/internal/pkg/logger"
	"api-gateway/internal/util"

	"github.com/gin-gonic/gin"
)

func JWTMiddleware() gin.HandlerFunc {
	return func(c *gin.Context) {
		token := c.GetHeader("Authorization")
		if token == "" {
			c.AbortWithStatusJSON(401, gin.H{"error": "missing token"})
			return
		}

		claims, err := util.VerifyToken(token)
		if err != nil {
			c.AbortWithStatusJSON(401, gin.H{"error": "invalid token"})
			return
		}

		userId, _ := claims["user_id"].(string)
		logger.Log.Printf("user_id = %v", claims["user_id"])
		role, _ := claims["role"].(string)

		c.Request.Header.Set("X-User-Id", userId)
		c.Request.Header.Set("X-Role", role)

		c.Next()
	}
}
