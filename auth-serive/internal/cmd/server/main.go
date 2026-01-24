package main

import (
	"auth-service/internal/config"
	"auth-service/internal/handler"
	"auth-service/internal/repository"
	"auth-service/internal/router"
	"auth-service/internal/service"
)

func main() {

	config.ConnectDatabase()
	userRepo := repository.NewUserRepository()
	authService := service.NewAuthService(userRepo)
	authHandler := handler.NewAuthHandler(authService)

	r := router.SetupRouter(authHandler)
	r.Run(":8081")
}
