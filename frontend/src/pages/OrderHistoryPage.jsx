import { useEffect, useState } from "react";
import api from "../api/axios";
import { useAuth } from "../context/AuthContext";

function OrderHistoryPage() {
  const { user } = useAuth();
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    if (user) {
      api.get("/orders/history", { params: { email: user.email } })
        .then(res => setOrders(res.data.data))
        .catch(err => console.error(err));
    }
  }, [user]);

  return (
    <div>
      <h1>Order History</h1>
      {orders.map(o => (
        <div key={o.id}>
          <p>Order ID: {o.id}</p>
          <p>Total: {o.totalAmount}</p>
        </div>
      ))}
    </div>
  );
}

export default OrderHistoryPage;