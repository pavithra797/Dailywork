import { useState } from "react";
import axios from "axios";

function OrderList() {
    const [orders, setOrders] = useState([]);
    const [show, setShow] = useState(false);

    const fetchOrders = async () => {
        try {
            const response = await axios.get("http://localhost:8080/order");
            setOrders(response.data);
        } catch (error) {
            console.error("Error fetching orders:", error);
        }
    };

    const handleToggle = () => {
        if (!show) {
            fetchOrders();
        }
        setShow(!show);
    };

    return (
        <div>
            <button onClick={handleToggle}>
                {show ? "Hide Orders" : "Show Orders"}
            </button>
            {show && (
                <div>
                    <h2>Saved Orders</h2>

                    {orders.map((order) => (
                        <div key={order.id}>
                            <strong>Order ID: {order.id}</strong>

                            <ul>
                                {order.orderLines.map((line) => (
                                    <li key={line.id}>
                                        {line.item} - {line.price}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}

export default OrderList;