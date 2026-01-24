package config

type Config struct {
	AuthServiceURL  string
	OrderServiceURL string
}

func Load() *Config {
	// hard code first to test, todo : get from OS
	return &Config{
		AuthServiceURL:  "http://localhost:8081",
		OrderServiceURL: "http://localhost:8080",
	}
}
