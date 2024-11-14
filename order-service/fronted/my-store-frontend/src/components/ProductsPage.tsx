import React, { useEffect, useState } from 'react';
import axios from 'axios';

// Тип для продукту відповідно до твоєї моделі
interface Product {
    id: number;
    name: string;
    description: string;
    price: number;
    quantity: number;
    category: string;
}

const ProductsPage: React.FC = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        // Отримуємо продукти з бекенду
        const fetchProducts = async () => {
            try {
                const response = await axios.get('http://localhost:8082/products');
                setProducts(response.data);
            } catch (err) {
                setError('Error fetching products');
                console.error('Error fetching products', err);
            } finally {
                setLoading(false);
            }
        };

        fetchProducts();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    return (
        <div>
            <h1>Available Products</h1>
            <div className="products-list">
                {products.map((product) => (
                    <div key={product.id} className="product-card">
                        <h2>{product.name}</h2>
                        <p>{product.description}</p>
                        <p><strong>Price:</strong> ${product.price}</p>
                        <p><strong>Category:</strong> {product.category}</p>
                        <p><strong>Quantity:</strong> {product.quantity}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ProductsPage;