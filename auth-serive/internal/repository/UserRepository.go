package repository

import (
	"auth-service/internal/config"
	"auth-service/internal/model"
	"errors"

	"gorm.io/gorm"
)

type UserRepository interface {
	Save(user *model.User) error
	FindByEmail(email string) (*model.User, error)
}

type userRepository struct{}

func NewUserRepository() UserRepository {
	return &userRepository{}
}

func (r *userRepository) Save(user *model.User) error {
	return config.DB.Create(user).Error
}

func (r *userRepository) FindByEmail(email string) (*model.User, error) {
	var user model.User
	err := config.DB.Where("email = ?", email).First(&user).Error

	if err != nil {
		if errors.Is(err, gorm.ErrRecordNotFound) {
			return nil, nil
		}
		return nil, err
	}
	return &user, nil
}
