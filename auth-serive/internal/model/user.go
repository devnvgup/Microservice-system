package model

import "time"

type User struct {
	ID        uint64 `gorm:"primaryKey"`
	Email     string `gorm:"unique;not null"`
	Password  string `gorm:"not null"`
	Role      string `gorm:"not null"`
	CreatedAt time.Time
}
