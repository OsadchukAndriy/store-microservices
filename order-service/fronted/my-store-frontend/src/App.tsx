import React from 'react';
import ProductsPage from './components/ProductsPage'; // Імпортуємо компонент з товарами
import './styles.css';

const App = () => {
    return (
        <div className="App">
            <h1>Ласкаво просимо до мого магазину!</h1>
            <ProductsPage /> {/* Виводимо сторінку з товарами */}
        </div>
    );
}

export default App;