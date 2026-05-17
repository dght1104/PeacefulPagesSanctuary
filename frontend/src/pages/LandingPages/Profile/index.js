// MUI
import { Container, Box, Chip, TextField } from "@mui/material";

// Layout
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

// Data + Component
import Card from "@mui/material/Card";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";
import bgImage from "assets/images/illustrations/illustration-reset.jpg";
import Grid from "@mui/material/Grid";
export default function ProfilePage() {
  const customer = {
    cus_id: 1,
    cus_name: "Nguyen Van A",
    cus_email: "vana@gmail.com",
    cus_phone: "0123456789",
    cus_username: "vana03",
    cus_group: "Silver",
    is_verified: true,
    is_active: true,
    total_spent: 3250000,
    cus_address: "4234234 asrowenrlsfn",
    cus_dob: "23/02/2000",
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

      <Card
        sx={{
          p: 3,
          mx: { xs: 2, lg: 10 },
          mt: -5,
          mb: 4,
          borderRadius: 3,
          boxShadow: "0 10px 30px rgba(0,0,0,0.08)",
          background: "#fff",
        }}
      >
        <Container>
          <Grid container spacing={3}>
            {/* SIDEBAR */}
            <Grid item xs={12} md={3}>
              <Box
                sx={{
                  p: 3,
                  borderRadius: 3,
                  border: "1px solid #eee",

                  top: 100,

                  // Canh giữa toàn bộ nội dung
                  display: "flex",
                  flexDirection: "column",
                  alignItems: "center",
                  justifyContent: "center",
                  textAlign: "center",
                }}
              >
                {/* AVATAR */}
                <Box
                  sx={{
                    width: 120,
                    height: 120,
                    borderRadius: "50%",
                    background: "#1976d2",
                    color: "#fff",
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center",
                    fontSize: 32,
                    fontWeight: "bold",
                    mb: 2,
                  }}
                >
                  {customer?.cus_name?.charAt(0)?.toUpperCase() || "U"}
                </Box>

                {/* USERNAME */}
                <MKTypography variant="h6" fontWeight="bold">
                  {customer?.cus_username}
                </MKTypography>

                {/* GROUP */}
                <Chip
                  label={customer?.cus_group || "Silver"}
                  color="primary"
                  size="small"
                  sx={{ mt: 1.5 }}
                />
              </Box>
            </Grid>

            {/* MAIN CONTENT */}
            <Grid item xs={12} md={9}>
              {/* PROFILE CARD */}
              <Box
                sx={{
                  p: 3,
                  borderRadius: 3,
                  border: "1px solid #eee",
                  mb: 3,
                }}
              >
                <MKTypography variant="h5" fontWeight="bold" mb={2}>
                  Profile Information
                </MKTypography>

                <Grid container spacing={2}>
                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Full Name" defaultValue={customer?.cus_name} />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Email" defaultValue={customer?.cus_email} />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Phone" defaultValue={customer?.cus_phone} />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <TextField fullWidth label="Birthday" defaultValue={customer?.cus_dob} />
                  </Grid>
                  <Grid item xs={12} md={12}>
                    <TextField fullWidth label="Address" defaultValue={customer?.cus_address} />
                  </Grid>
                </Grid>

                <Box mt={2}>
                  <Chip label="Save Changes" color="success" clickable />
                </Box>
              </Box>

              {/* STATS */}
              <Grid container spacing={2} mb={3}>
                <Grid item xs={12} md={4}>
                  <Box sx={{ p: 2, border: "1px solid #eee", borderRadius: 3 }}>
                    <MKTypography variant="h6">Orders</MKTypography>
                    <MKTypography variant="h4">3</MKTypography>
                  </Box>
                </Grid>

                <Grid item xs={12} md={4}>
                  <Box sx={{ p: 2, border: "1px solid #eee", borderRadius: 3 }}>
                    <MKTypography variant="h6">Spent</MKTypography>
                    <MKTypography variant="h4">{customer?.total_spent}</MKTypography>
                  </Box>
                </Grid>

                <Grid item xs={12} md={4}>
                  <Box sx={{ p: 2, border: "1px solid #eee", borderRadius: 3 }}>
                    <MKTypography variant="h6">Coupons</MKTypography>
                    <MKTypography variant="h4">3</MKTypography>
                  </Box>
                </Grid>
              </Grid>

              {/* RECENT ORDERS */}
            </Grid>
          </Grid>
        </Container>
      </Card>
      <MKBox px={0} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}
