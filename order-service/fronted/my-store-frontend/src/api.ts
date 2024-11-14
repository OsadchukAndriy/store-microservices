import axios from 'axios';

const API_URL = 'http://localhost:8082'; // Заміни на URL свого бекенду

// Функція для отримання списку продуктів
export const getProducts = async () => {
    try {
        const response = await axios.get(`${API_URL}/products`);
        return response.data;
    } catch (error) {
        console.error('Error fetching products', error);
    }
};

// Можеш додати інші функції для роботи з іншими мікросервісами