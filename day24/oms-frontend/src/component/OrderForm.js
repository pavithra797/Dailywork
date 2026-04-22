import { useState } from "react";
import axios from "axios";
import "./OrderForm.css";
import OrderList from "./OrderList";

function OrderForm() {
    const [item, setItem] = useState("");
    const [price, setPrice] = useState("");
    const [quantity, setQuantity] = useState("");
    const [orderLines, setOrderLines] = useState([]);
    const [orders, setOrders] = useState([]);
    const [error, setError] = useState("");

    const handleAdd = () => {
        if (!item || !price || !quantity) {
            setError("All fields are required");
            return;
        }

        if (parseFloat(price) < 0 || parseInt(quantity) < 1) {
            setError("Invalid price or quantity");
            return;
        }

        const newItem = {
            item: item,
            price: parseFloat(price),
            quantity: parseInt(quantity)
        };

        setOrderLines([...orderLines, newItem]);

        setItem("");
        setPrice("");
        setQuantity("");
        setError("");
    };

    const handleRemove = (index) => {
        const updatedList = orderLines.filter((_, i) => i !== index);
        setOrderLines(updatedList);
    };

    const handleSubmit = async (e) => {
    e.preventDefault();

    if (orderLines.length === 0) {
        setError("Add at least one item");
        return;
    }

    try {
        const response = await axios.post("http://localhost:8080/order", {
            orderLines: orderLines
        });

        setOrders([...orders, response.data]);
        setOrderLines([]);
        setError("");

    } catch (error) {
        console.error("Error:", error);
        setError("Failed to create order");
    }
};

    return (
        <div className="container">
            <h2>Add Order</h2>

            <div className="input-group">
                <input
                    type="text"
                    placeholder="Item"
                    value={item}
                    onChange={(e) => setItem(e.target.value)}
                />

                <input
                    type="number"
                    step="0.01"
                    placeholder="Price"
                    value={price}
                    onChange={(e) => setPrice(e.target.value)}
                />

                <input
                    type="number"
                    placeholder="Quantity"
                    value={quantity}
                    onChange={(e) => setQuantity(e.target.value)}
                />

                <button className="sub-btn" onClick={handleAdd}>
                    Add
                </button>
            </div>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <ul>
                {orderLines.map((line, index) => (
                    <li key={index}>
                        {line.item} - {line.price} x {line.quantity}
                        <button
                            className="remove-btn"
                            onClick={() => handleRemove(index)}
                        >
                            Remove
                        </button>
                    </li>
                ))}
            </ul>

            <button className="submit-btn" onClick={handleSubmit}>
                Submit Final Order
            </button>

            <div style={{ marginTop: "10px" }}>
                <OrderList />
            </div>

            {orders.length > 0 && (
                <div style={{ marginTop: "15px" }}>
                    <h3>Created Orders</h3>
                    <ul>
                        {orders.map((id, index) => (
                            <li key={index}>Order ID: {id}</li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
}

export default OrderForm;