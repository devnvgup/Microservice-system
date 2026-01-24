package service

import (
	"auth-service/internal/dto"
)

type AuthService interface {
	Login(req dto.LoginRequest) (*dto.TokenResponse, error)
	Register(req dto.RegisterRequest) error
}
