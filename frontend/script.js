const baseUrl = "http://localhost:8081/api";

//Функция обработки ответа
// const handleResponse = async (response) => {
//     if (!response.ok) {
//         // Попытка получить текст ошибки
//         const errorText = await response.text();
//         throw new Error(errorText || `HTTP Error: ${response.status}`);
//     }
//     return response.json();
// };

//Функция обработки ответа
const handleResponse = async (response) => {
    if (!response.ok) {
        const errorText = await response.text(); // Получаем текст ошибки
        let errorJson;

        try {
            errorJson = JSON.parse(errorText); // Пробуем разобрать текст как JSON
        } catch {
            errorJson = null; // Если разбор JSON не удался
        }

        // Извлекаем статус и сообщение ошибки
        const status = response.status;
        const message = errorJson?.error?.message || errorJson?.message || `HTTP Error: ${status}`;

        throw new Error(`${status} - ${message}`);
    }
    return response.json();
};

const getProducts = async () => {
    try {
        const response = await fetch(`${baseUrl}/inventory/products`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        const result = await handleResponse(response);

        // Если результат содержит товары, выводим их
        if (Array.isArray(result)) {
            displayProducts(result);
        } else {
            alert("No products available.");
        }
    } catch (error) {
        console.error("Error fetching products:", error.message);
        alert(`Failed to fetch products: ${error.message}`);
    }
};

// Отображение списка товаров
const displayProducts = (products) => {
    const productListContainer = document.getElementById("product-list");
    if (!productListContainer) {
        console.error("Product list container not found.");
        return;
    }

    productListContainer.innerHTML = '';

    if (products.length === 0) {
        productListContainer.innerHTML = '<p>No products available.</p>';
        return;
    }

    products.forEach((product) => {
        const productElement = document.createElement("div");
        productElement.classList.add("product-item");
        productElement.innerHTML = `
            <strong>${product.name}</strong><br>
            Quantity: ${product.quantity}<br>
            Price: $${product.price}<br><br>
        `;
        productListContainer.appendChild(productElement);
    });
};

// Добавить новый товар
const addProduct = async () => {
    const productName = document.getElementById("product-name").value;
    const eventType = document.getElementById("event-type").value;
    const quantity = parseInt(document.getElementById("quantity").value, 10);
    const price = parseInt(document.getElementById("product-price").value, 10);
    const timestamp = new Date().toISOString(); // Текущее время

    const productData = {
        productName,
        eventType,
        quantity,
        price,
        timestamp,
    };

    try {
        const response = await fetch(`${baseUrl}/inventory/event`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(productData),
        });

        const result = await handleResponse(response);

        // Успешное добавление товара
        console.log("Product added successfully:", result);
        alert("Product added successfully!");

        // // Сохраняем введенные данные в LocalStorage
        // localStorage.setItem("productData", JSON.stringify(productData));
    } catch (error) {
        // Обработка ошибок
        console.error("Error adding/removing product:", error.message);
        alert(`Failed to add/remove product: ${error.message}`);
    }
};

// Регистрация пользователя
const register = async () => {
    const username = document.getElementById("register-username").value;
    const password = document.getElementById("register-password").value;

    try {
        const response = await fetch(`${baseUrl}/user/registration`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        });

        const result = await handleResponse(response);

        // Успешная регистрация
        console.log("Registration successful:", result);
        alert("Registration successful!");
    } catch (error) {
        // Обработка ошибок
        console.error("Registration error:", error.message);
        alert(`Registration failed: ${error.message}`);
    }
};

// Аутентификация пользователя
const authenticate = async () => {
    const username = document.getElementById("auth-username").value;
    const password = document.getElementById("auth-password").value;

    try {
        const response = await fetch(`${baseUrl}/user/auth`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ username, password }),
        });

        const result = await handleResponse(response);

        // Сохранение токена в localStorage
        console.log("Authentication successful:", result);
        localStorage.setItem("token", result.token);
        alert("Authentication successful!");
    } catch (error) {
        // Обработка ошибок
        console.error("Authentication error:", error.message);
        alert(`Authentication failed: ${error.message}`);
    }
};

// Получение страницы пользователя
const getUserPage = async () => {
    const token = localStorage.getItem("token");

    if (!token) {
        alert("You are not authenticated. Please log in.");
        return;
    }

    try {
        const response = await fetch(`${baseUrl}/user/page`, {
            method: "GET",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        const result = await handleResponse(response);

        // Отображение данных пользователя
        console.log("User page data:", result);
        alert(`User page: ${JSON.stringify(result)}`);
    } catch (error) {
        // Обработка ошибок
        console.error("Error fetching user page:", error.message);
        alert(`Failed to fetch user page: ${error.message}`);
    }
};
