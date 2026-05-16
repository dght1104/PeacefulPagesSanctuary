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
import { Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
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
  const [voucher, setVoucher] = useState("");
  const [shippingVoucher, setShippingVoucher] = useState("");

  const [discount, setDiscount] = useState(0); // giảm trên subtotal
  const [shippingDiscount, setShippingDiscount] = useState(0); // giảm trên phí ship

  const [openQR, setOpenQR] = useState(false);

  const [form, setForm] = useState({
    fullName: "",
    email: "",
    phone: "",
    address: "",
    city: "",
    notes: "",
  });

  // =======================
  // SUBTOTAL
  // =======================
  const subtotal = useMemo(() => {
    return checkoutItems.reduce((sum, item) => sum + item.price * (item.quantity || 1), 0);
  }, []);

  // =======================
  // SHIPPING
  // =======================
  const shipping = 5;

  // Phí ship sau khi áp dụng shipping voucher
  const finalShipping = Math.max(0, shipping - shippingDiscount);

  // =======================
  // TOTAL
  // =======================
  const total = subtotal + finalShipping - discount;

  // =======================
  // FORM CHANGE
  // =======================
  const handleChange = (field) => (e) => {
    setForm({
      ...form,
      [field]: e.target.value,
    });
  };

  // =======================
  // PRODUCT VOUCHER
  // =======================
  const handleApplyVoucher = () => {
    const code = voucher.trim().toUpperCase();

    if (code === "SALE10") {
      setDiscount(subtotal * 0.1); // giảm 10%
    } else if (code === "FREE5") {
      setDiscount(5); // giảm $5
    } else {
      setDiscount(0);
      alert("Invalid voucher");
    }
  };

  // =======================
  // SHIPPING VOUCHER
  // =======================
  const handleApplyShippingVoucher = () => {
    const code = shippingVoucher.trim().toUpperCase();

    if (code === "FREESHIP") {
      setShippingDiscount(shipping); // miễn phí ship
    } else if (code === "SHIP3") {
      setShippingDiscount(Math.min(3, shipping)); // giảm tối đa $3
    } else {
      setShippingDiscount(0);
      alert("Invalid shipping voucher");
    }
  };

  // =======================
  // PLACE ORDER
  // =======================
  const handlePlaceOrder = () => {
    if (paymentMethod === "bank") {
      setOpenQR(true);
      return;
    }

    console.log({
      customerInfo: form,
      paymentMethod,
      items: checkoutItems,
      subtotal,
      shipping,
      shippingDiscount,
      finalShipping,
      discount,
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
            py: 1,
            px: { xs: 2, md: 4, lg: 8 },
          }}
        >
          <Grid container spacing={4}>
            {/* LEFT SIDE - CUSTOMER FORM */}
            <Grid item xs={12} lg={6}>
              <Card sx={{ p: 4, borderRadius: 3 }}>
                <Typography variant="h4" align="center" fontWeight="bold" mb={2}>
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
                {/* Vouchers */}
                <Typography variant="h4" fontWeight="bold" mt={2} mb={2}>
                  Vouchers
                </Typography>

                <Grid container spacing={2}>
                  {/* Product Voucher */}
                  <Grid item xs={12} md={6}>
                    <Box display="flex" gap={1}>
                      <MKInput
                        fullWidth
                        label="Product voucher"
                        value={voucher}
                        onChange={(e) => setVoucher(e.target.value)}
                      />

                      <MKButton
                        variant="contained"
                        color="info"
                        size="small"
                        onClick={handleApplyVoucher}
                        sx={{
                          minWidth: "70px",
                          px: 2,
                          py: 1,
                          whiteSpace: "nowrap",
                        }}
                      >
                        Apply
                      </MKButton>
                    </Box>
                  </Grid>

                  {/* Shipping Voucher */}
                  <Grid item xs={12} md={6}>
                    <Box display="flex" gap={1}>
                      <MKInput
                        fullWidth
                        label="Shipping voucher"
                        value={shippingVoucher}
                        onChange={(e) => setShippingVoucher(e.target.value)}
                      />

                      <MKButton
                        variant="contained"
                        color="success"
                        size="small"
                        onClick={handleApplyShippingVoucher}
                        sx={{
                          minWidth: "70px",
                          px: 2,
                          py: 1,
                          whiteSpace: "nowrap",
                        }}
                      >
                        Apply
                      </MKButton>
                    </Box>
                  </Grid>
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
                <Typography align="center" variant="h4" fontWeight="bold" mb={3}>
                  Order Summary
                </Typography>

                <TableContainer sx={{ overflowX: "auto" }}>
                  <Table
                    size="small"
                    sx={{
                      tableLayout: "fixed",
                      width: "100%",

                      "& .MuiTableCell-root": {
                        px: 1.5,
                        py: 1.8,
                        verticalAlign: "middle",
                        whiteSpace: "nowrap",
                      },
                    }}
                  >
                    {/* Column Width */}
                    <colgroup>
                      <col style={{ width: "52%" }} />
                      <col style={{ width: "20%" }} />
                      <col style={{ width: "8%" }} />
                      <col style={{ width: "20%" }} />
                    </colgroup>

                    {/* Body */}
                    <TableBody>
                      <TableRow>
                        {/* Product */}
                        <TableCell
                          align="left"
                          sx={{
                            fontWeight: 500,
                            fontSize: "1rem",
                            borderBottom: "1px solid",
                            borderColor: "divider",

                            // canh thẳng với text bên dưới image
                            pl: "68px",
                          }}
                        >
                          Product
                        </TableCell>

                        {/* Price */}
                        <TableCell
                          align="center"
                          sx={{
                            fontWeight: 500,
                            fontSize: "1rem",
                            borderBottom: "1px solid",
                            borderColor: "divider",
                          }}
                        >
                          Price
                        </TableCell>

                        {/* Qty */}
                        <TableCell
                          align="center"
                          sx={{
                            fontWeight: 500,
                            fontSize: "1rem",
                            borderBottom: "1px solid",
                            borderColor: "divider",
                          }}
                        >
                          Qty
                        </TableCell>

                        {/* Total */}
                        <TableCell
                          align="right"
                          sx={{
                            fontWeight: 500,
                            fontSize: "1rem",
                            borderBottom: "1px solid",
                            borderColor: "divider",
                          }}
                        >
                          Total
                        </TableCell>
                      </TableRow>
                      {checkoutItems.map((item, index) => (
                        <TableRow
                          key={`${item.id}-${index}`}
                          sx={{
                            "&:last-child td": {
                              borderBottom: "none",
                            },
                          }}
                        >
                          {/* Product */}
                          <TableCell align="left">
                            <Box display="flex" alignItems="center" gap={1.5}>
                              <Box
                                component="img"
                                src={item.image}
                                alt={item.title}
                                sx={{
                                  width: 40,
                                  height: 55,
                                  objectFit: "cover",
                                  borderRadius: 1,
                                  flexShrink: 0,
                                }}
                              />

                              <Typography
                                variant="body2"
                                fontWeight="600"
                                sx={{
                                  fontSize: "0.85rem",
                                  overflow: "hidden",
                                  textOverflow: "ellipsis",
                                  whiteSpace: "nowrap",
                                }}
                              >
                                {item.title}
                              </Typography>
                            </Box>
                          </TableCell>

                          {/* Price */}
                          <TableCell align="center">
                            <Typography
                              variant="body2"
                              sx={{
                                fontSize: "0.85rem",
                              }}
                            >
                              ${item.price.toFixed(2)}
                            </Typography>
                          </TableCell>

                          {/* Qty */}
                          <TableCell align="center">
                            <Typography
                              variant="body2"
                              color="text.secondary"
                              sx={{
                                fontSize: "0.85rem",
                              }}
                            >
                              {item.quantity}
                            </Typography>
                          </TableCell>

                          {/* Total */}
                          <TableCell align="right">
                            <Typography
                              variant="body2"
                              sx={{
                                fontSize: "0.85rem",
                              }}
                            >
                              ${(item.price * item.quantity).toFixed(2)}
                            </Typography>
                          </TableCell>
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </TableContainer>

                <Divider sx={{ my: 2 }} />

                {/* Summary */}
                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography variant="body2" color="text.secondary">
                    Subtotal
                  </Typography>
                  <Typography variant="body2">${subtotal.toFixed(2)}</Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography variant="body2" color="text.secondary">
                    Shipping
                  </Typography>
                  <Typography variant="body2">
                    {shipping === 0 ? "Free" : `$${shipping.toFixed(2)}`}
                  </Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography variant="body2" color="text.secondary">
                    Discount
                  </Typography>
                  <Typography
                    variant="body2"
                    color={discount > 0 ? "success.main" : "text.secondary"}
                  >
                    -${discount.toFixed(2)}
                  </Typography>
                </Box>

                <Box display="flex" justifyContent="space-between" mb={1}>
                  <Typography variant="body2" color="text.secondary">
                    Shipping Discount
                  </Typography>
                  <Typography
                    variant="body2"
                    color={shippingDiscount > 0 ? "success.main" : "text.secondary"}
                  >
                    -${shippingDiscount.toFixed(2)}
                  </Typography>
                </Box>

                <Divider sx={{ my: 2 }} />

                <Box display="flex" justifyContent="space-between" mb={3}>
                  <Typography variant="h6">Total</Typography>
                  <Typography variant="h6">${total.toFixed(2)}</Typography>
                </Box>

                <MKButton
                  fullWidth
                  color="info"
                  size="large"
                  onClick={handlePlaceOrder}
                  sx={{
                    borderRadius: 2,
                    py: 1.2,
                    fontWeight: 600,
                    textTransform: "none",
                  }}
                >
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
