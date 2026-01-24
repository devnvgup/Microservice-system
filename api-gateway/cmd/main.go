package main

import (
	"api-gateway/internal/config"
	"api-gateway/internal/router"
	"log"
)

func main() {
	cfg := config.Load()

	r := router.SetupRouter(cfg)
	if err := r.Run(":7080"); err != nil {
		log.Fatal(err)
	}
}
