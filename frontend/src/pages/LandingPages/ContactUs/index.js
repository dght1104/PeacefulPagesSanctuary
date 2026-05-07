/*
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================
*/

import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";

// Material Kit 2 React examples
import DefaultNavbar from "examples/Navbars/DefaultNavbar";
import DefaultFooter from "examples/Footers/DefaultFooter";

// Routes
import routes from "routes";
import footerRoutes from "footer.routes";

// Image
import bgImage from "assets/images/illustrations/illustration-reset.jpg";

function ContactUs() {
  return (
    <>
      <DefaultNavbar routes={routes} transparent light />

      {/* Hero Section */}
      <MKBox
        minHeight="75vh"
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
            lg={7}
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
              Contact Us
            </MKTypography>

            <MKTypography variant="body1" color="white" opacity={0.8} mt={1}>
              Have questions or feedback? We will love to hear from you. Send us a message and we’ll
              respond as soon as possible.
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>

      {/* Contact Form Card */}
      <Card
        sx={{
          p: 4,
          mx: { xs: 2, lg: 3 },
          mt: -8,
          mb: 4,
          boxShadow: ({ boxShadows: { xxl } }) => xxl,
        }}
      >
        <Container>
          <Grid container spacing={3} justifyContent="center">
            <Grid item xs={12} md={8} lg={6}>
              <MKBox mb={3}>
                <MKTypography variant="h3" mb={1}>
                  Send Message
                </MKTypography>

                <MKTypography variant="body2" color="text">
                  Fill out the form below and our team will get back to you shortly.
                </MKTypography>
              </MKBox>

              <MKBox component="form" role="form">
                <Grid container spacing={3}>
                  <Grid item xs={12} md={6}>
                    <MKInput fullWidth label="Full Name" />
                  </Grid>

                  <Grid item xs={12} md={6}>
                    <MKInput type="email" fullWidth label="Email" />
                  </Grid>

                  <Grid item xs={12}>
                    <MKInput multiline rows={6} fullWidth label="Message" />
                  </Grid>
                </Grid>

                <MKBox mt={4} textAlign="center">
                  <MKButton variant="gradient" color="info" size="large">
                    Send Message
                  </MKButton>
                </MKBox>
              </MKBox>
            </Grid>
          </Grid>
        </Container>
      </Card>

      <MKBox pt={6} px={1} mt={6}>
        <DefaultFooter content={footerRoutes} />
      </MKBox>
    </>
  );
}

export default ContactUs;
