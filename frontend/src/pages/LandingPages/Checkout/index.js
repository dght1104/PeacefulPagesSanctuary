import { useMemo, useState } from "react";
// Import thêm
// MUI
import {
  Container,
  Grid,
  Card,
  Typography,
  Divider,
  Box,
  RadioGroup,
  FormControlLabel,
  Radio,
  TextField,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@mui/material";
import { useNavigate } from "react-router-dom";
import MKButton from "components/MKButton";
import MKInput from "components/MKInput";

// Layout
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
const checkoutItems = [
  {
    id: 1,
    title: "Atomic Habits",
    quantity: 2,
    price: 18.99,
    image:
      "https://images.unsplash.com/photo-1512820790803-83ca734da794?auto=format&fit=crop&w=800&q=80",
  },
  {
    id: 2,
    title: "Clean Code",
    quantity: 1,
    price: 24.99,
    image:
      "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=80",
  },
  {
    id: 2,
    title: "Clean Code",
    quantity: 1,
    price: 24.99,
    image:
      "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=80",
  },
  {
    id: 2,
    title: "Clean Code",
    quantity: 1,
    price: 24.99,
    image:
      "https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?auto=format&fit=crop&w=800&q=80",
  },
];

export default function Checkout() {
  const navigate = useNavigate();

  const [paymentMethod, setPaymentMethod] = useState("cod");

  const [form, setForm] = useState({
    fullName: "",
    email: "",
    phone: "",
    address: "",
    city: "",
    notes: "",
  });

  const [voucher, setVoucher] = useState("");
  const [discount, setDiscount] = useState(0);

  // 1. SUBTOTAL
  const subtotal = useMemo(() => {
    return checkoutItems.reduce((sum, item) => sum + item.price * (item.quantity || 1), 0);
  }, [checkoutItems]);

  // 2. SHIPPING
  const shipping = 5;

  // 3. TOTAL (SAU TẤT CẢ)
  const total = subtotal + shipping - discount;

  // FORM CHANGE
  const handleChange = (field) => (e) => {
    setForm({ ...form, [field]: e.target.value });
  };

  // VOUCHER
  const handleApplyVoucher = () => {
    if (voucher === "SALE10") setDiscount(subtotal * 0.1);
    else if (voucher === "FREE5") setDiscount(5);
    else {
      setDiscount(0);
      alert("Invalid voucher");
    }
  };

  // State
  const [openQR, setOpenQR] = useState(false);

  // Sửa handlePlaceOrder
  const handlePlaceOrder = () => {
    // Nếu chọn Bank Transfer thì mở popup QR
    if (paymentMethod === "bank") {
      setOpenQR(true);
      return;
    }

    // COD hoặc PayPal
    console.log({
      customerInfo: form,
      paymentMethod,
      items: checkoutItems,
      total,
    });

    alert("Order placed successfully!");
    navigate("/order-success");
  };

  return (
    <>
      <DefaultNavbar
        routes={routes}
        action={{
          type: "external",
          route: "https://www.creative-tim.com/product/material-kit-react",
          label: "free download",
          color: "default",
        }}
      />
      <MKBox
        minHeight="50vh"
        width="100%"
        sx={{
          backgroundImage: ({ functions: { linearGradient, rgba }, palette: { gradients } }) =>
            `${linearGradient(
              rgba(gradients.dark.main, 0.6),
              rgba(gradients.dark.state, 0.6)
            )}, url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          display: "grid",
          placeItems: "center",
        }}
      >
        <Container>
          <Grid
            container
            item
            xs={12}
            lg={8}
            justifyContent="center"
            alignItems="center"
            flexDirection="column"
            sx={{ mx: "auto", textAlign: "center" }}
          >
            <MKTypography
              variant="h1"
              color="white"
              sx={({ breakpoints, typography: { size } }) => ({
                [breakpoints.down("md")]: {
                  fontSize: size["3xl"],
                },
              })}
            >
              Work with an amazing design
            </MKTypography>
            <MKTypography variant="body1" color="white" opacity={0.8} mt={0} mb={0}>
              We&apos;re constantly trying to express ourselves and actualize our dreams. If you
              have the opportunity to play this game
            </MKTypography>
            <MKTypography variant="h6" color="white" mt={4} mb={1}>
              Find us on
            </MKTypography>
            <MKBox display="flex" justifyContent="center" alignItems="center">
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-facebook" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-instagram" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#" mr={3}>
                <i className="fab fa-twitter" />
              </MKTypography>
              <MKTypography component="a" variant="body1" color="white" href="#">
                <i className="fab fa-google-plus" />
              </MKTypography>
            </MKBox>
          </Grid>
        </Container>
      </MKBox>

      {/* PAGE */}
      <Card
        sx={{
          p: 2,
          mx: { xs: 2, lg: 3 },
          mt: -5,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container
          maxWidth={false}
          sx={{
            py: 3,
            px: { xs: 2, md: 4, lg: 8 },
          }}
        >
          <Grid container spacing={4}>
            {/* LEFT SIDE - CUSTOMER FORM */}
            <Grid item xs={12} lg={6}>
              <Card sx={{ p: 4, borderRadius: 3 }}>
                <Typography variant="h4" fontWeight="bold" mb={2}>
                  Shipping Information
                </Typography>

                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <MKInput
                      fullWidth
                      label="Full Name"
                      value={form.fullName}
                      onChange={handleChange("fullName")}
                    />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <MKInput
                      fullWidth
                      label="Email"
                      value={form.email}
                      onChange={handleChange("email")}
                    />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <MKInput
                      fullWidth
                      label="Phone Number"
                      value={form.phone}
                      onChange={handleChange("phone")}
                    />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <MKInput
                      fullWidth
                      label="Postal Code"
                      value={form.PostalCode}
                      onChange={handleChange("Postal Code")}
                    />
                  </Grid>

                  <Grid item xs={12}>
                    <MKInput
                      fullWidth
                      label="Shipping Address"
                      value={form.address}
                      onChange={handleChange("address")}
                    />
                  </Grid>

                  <Grid item xs={12}>
                    <TextField
                      fullWidth
                      multiline
                      rows={1}
                      label="Order Notes"
                      value={form.notes}
                      onChange={handleChange("notes")}
                    />
                  </Grid>
                </Grid>
                {/* Voucher */}
                <Typography variant="h4" fontWeight="bold" mt={2} mb={2}>
                  Voucher
                </Typography>

                <Grid mb={1}>
                  <Box display="flex" gap={1}>
                    <MKInput
                      fullWidth
                      label="Enter voucher code"
                      value={voucher}
                      onChange={(e) => setVoucher(e.target.value)}
                    />

                    <MKButton variant="contained" color="info" onClick={handleApplyVoucher}>
                      Apply
                    </MKButton>
                  </Box>
                </Grid>

                {/* Payment Method */}
                <Typography variant="h5" fontWeight="bold" mt={2} mb={2}>
                  Payment Method
                </Typography>

                <RadioGroup
                  value={paymentMethod}
                  onChange={(e) => setPaymentMethod(e.target.value)}
                >
                  <FormControlLabel
                    value="cod"
                    control={<Radio />}
                    label="Cash on Delivery (COD)"
                  />
                  <FormControlLabel value="bank" control={<Radio />} label="Bank Transfer" />
                </RadioGroup>
              </Card>
            </Grid>

            {/* RIGHT SIDE - ORDER SUMMARY */}
            <Grid item xs={12} lg={6}>
              <Card
                sx={{
                  p: 3,
                  borderRadius: 3,
                  position: "sticky",
                  top: 100,
                }}
              >
                <Typography variant="h5" fontWeight="bold" mb={3}>
                  Order Summary
                </Typography>

                {checkoutItems.map((item, index) => (
                  <Box
                    key={`${item.id}-${index}`}
                    display="flex"
                    alignItems="center"
                    gap={2}
                    mb={2}
                  >
                    {/* Product Image */}
                    <Box
                      component="img"
                      src={item.image}
                      alt={item.title}
                      sx={{
                        width: 60,
                        height: 80,
                        objectFit: "cover",
                        borderRadius: 2,
                      }}
                    />

                    {/* Title + Qty + Total */}
                    <Box
                      flex={1}
                      display="grid"
                      gridTemplateColumns="3fr 1fr 1fr"
                      alignItems="center"
                      columnGap={2}
                    >
                      {/* Title - 3 phần */}
                      <Typography
                        fontWeight="bold"
                        variant="body2"
                        sx={{
                          overflow: "hidden",
                          textOverflow: "ellipsis",
                          whiteSpace: "nowrap",
                        }}
                      >
                        {item.title}
                      </Typography>

                      {/* Quantity - 1 phần */}
                      <Typography
                        variant="body2"
                        color="text.secondary"
                        textAlign="center"
                        whiteSpace="nowrap"
                      >
                        Qty: {item.quantity}
                      </Typography>

                      {/* Total - 1 phần */}
                      <Typography fontWeight="bold" textAlign="right" whiteSpace="nowrap">
                        ${(item.price * item.quantity).toFixed(2)}
                      </Typography>
                    </Box>
                  </Box>
                ))}
                <Divider sx={{ my: 2 }} />

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Subtotal</Typography>
                  <Typography>${subtotal.toFixed(2)}</Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Shipping</Typography>
                  <Typography>{shipping === 0 ? "Free" : `$${shipping.toFixed(2)}`}</Typography>
                </Box>
                {/* Discount */}
                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography color="text.secondary">Voucher</Typography>
                  <Typography color={discount > 0 ? "success.main" : "text.secondary"}>
                    -${discount.toFixed(2)}
                  </Typography>
                </Box>
                <Divider sx={{ my: 2 }} />

                <Box display="flex" justifyContent="space-between" mb={3}>
                  <Typography variant="h6" fontWeight="bold">
                    Total
                  </Typography>
                  <Typography variant="h6" fontWeight="bold">
                    ${total.toFixed(2)}
                  </Typography>
                </Box>

                <MKButton fullWidth color="info" size="large" onClick={handlePlaceOrder}>
                  Place Order
                </MKButton>
              </Card>
            </Grid>
          </Grid>
        </Container>
      </Card>
      {/* QR Code Dialog */}
      <Dialog open={openQR} onClose={() => setOpenQR(false)} maxWidth="xs" fullWidth>
        <DialogTitle sx={{ textAlign: "center", fontWeight: "bold" }}>Bank Transfer</DialogTitle>
        <DialogContent sx={{ textAlign: "center", py: 3 }}>
          <Typography variant="body2" color="text.secondary" mb={2}>
            Please scan the QR code below to complete your payment.
          </Typography>
          <Box
            component="img"
            src="https://api.qrserver.com/v1/create-qr-code/?size=250x250&data=https://peacefulpagessanctuary-fe.onrender.com"
            alt="Bank Transfer QR"
            sx={{ width: 250, height: 250, mx: "auto", borderRadius: 2 }}
          />
          <Typography mt={2} fontWeight="bold">
            ABC Bank - 7803945-023478243589
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Account Name: PEACEFUL PAGES SANCTUARY
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Amount: ${total.toFixed(2)}
          </Typography>
        </DialogContent>
        <DialogActions sx={{ px: 3, pb: 3 }}>
          <MKButton color="dark" onClick={() => setOpenQR(false)}>
            Close
          </MKButton>
          <MKButton
            color="info"
            onClick={() => {
              setOpenQR(false);
              console.log({ customerInfo: form, paymentMethod, items: checkoutItems, total });
              alert("Payment confirmed!");
              navigate("/order-success");
            }}
          >
            {" "}
            I Have Paid{" "}
          </MKButton>{" "}
        </DialogActions>
      </Dialog>
      <MKBox px={0} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
