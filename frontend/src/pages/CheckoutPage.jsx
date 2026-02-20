import { useState } from "react";
import api from "../api/axios";
import { useAuth } from "../context/AuthContext";

function CheckoutPage() {
  const { user } = useAuth();
  const [productCoupon, setProductCoupon] = useState("");
  const [shippingCoupon, setShippingCoupon] = useState("");
  const [shippingFee, setShippingFee] = useState(20000);

  const checkout = () => {
    api.post("/orders/checkout", null, {
      params: {
        email: user.email,
        productCouponCode: productCoupon || null,
        shippingCouponCode: shippingCoupon || null,
        shippingFee
      }
    })
    .then(res => {
      alert("Order placed successfully");
      console.log(res.data);
    })
    .catch(err => alert(err.response?.data?.message));
  };

  return (
    <div>
      <h1>Checkout</h1>
      <input
        placeholder="Product Coupon"
        value={productCoupon}
        onChange={e => setProductCoupon(e.target.value)}
      />
      <input
        placeholder="Shipping Coupon"
        value={shippingCoupon}
        onChange={e => setShippingCoupon(e.target.value)}
      />
      <input
        type="number"
        value={shippingFee}
        onChange={e => setShippingFee(e.target.value)}
      />
      <button onClick={checkout}>Confirm</button>
    </div>
  );
}

export default CheckoutPage;