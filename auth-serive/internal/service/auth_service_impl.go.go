package service

import (
	"auth-service/internal/dto"
	"auth-service/internal/model"
	"auth-service/internal/repository"
	"auth-service/internal/util"
	"errors"
)

type authServiceImpl struct {
	userRepo repository.UserRepository
}

func NewAuthService(userRepo repository.UserRepository) AuthService {
	return &authServiceImpl{
		userRepo: userRepo,
	}
}

func (s *authServiceImpl) Login(req dto.LoginRequest) (*dto.TokenResponse, error) {

	existingUser, err := s.userRepo.FindByEmail(req.Email)
	if err != nil {
		return nil, err
	}

	if existingUser == nil {
		return nil, errors.New("Invalid Email or Password")
	}

	err = util.CheckPassword(existingUser.Password, req.Password)
	if err != nil {
		return nil, errors.New("Invalid Email or Password")
	}

	access, _ := util.GenerateAccessToken(existingUser.ID, existingUser.Role)
	refresh, _ := util.GenerateRefreshToken(existingUser.ID)

	return &dto.TokenResponse{
		AccessToken:  access,
		RefreshToken: refresh,
	}, nil
}

func (s *authServiceImpl) Register(req dto.RegisterRequest) error {
	if req.Password != req.ConfirmPassword {
		return errors.New("password and confirm password do not match")
	}
	existingUser, err := s.userRepo.FindByEmail(req.Email)
	if err != nil {
		return err
	}
	if existingUser != nil {
		return errors.New("email already exists")
	}
	hashPassword, err := util.HashPassword(req.Password)
	if err != nil {
		return err
	}

	user := &model.User{
		Email:    req.Email,
		Password: hashPassword,
		Role:     "USER",
	}

	err = s.userRepo.Save(user)
	if err != nil {
		return err
	}
	return nil
}
