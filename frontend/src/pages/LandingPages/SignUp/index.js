/**
=========================================================
* Material Kit 2 React - Sign Up Page
=========================================================
*/

import { useState } from "react";

// react-router-dom components
import { Link } from "react-router-dom";

// @mui material components
import Card from "@mui/material/Card";
import Grid from "@mui/material/Grid";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";

// Material Kit 2 React example components
import SimpleFooter from "examples/Footers/SimpleFooter";

// Images
import bgImage from "assets/images/bg-sign-in-basic.jpeg";

function SignUp() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
    confirmPassword: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    console.log(formData);
  };

  return (
    <>
      <MKBox
        position="absolute"
        top={0}
        left={0}
        zIndex={1}
        width="100%"
        minHeight="100vh"
        sx={{
          backgroundImage: ({ functions: { linearGradient, rgba }, palette: { gradients } }) =>
            `${linearGradient(
              rgba(gradients.dark.main, 0.6),
              rgba(gradients.dark.state, 0.6)
            )}, url(${bgImage})`,
          backgroundSize: "cover",
          backgroundPosition: "center",
          backgroundRepeat: "no-repeat",
        }}
      />

      <MKBox
        px={1}
        width="100%"
        minHeight="100vh"
        mx="auto"
        position="relative"
        zIndex={2}
        display="flex"
        alignItems="center"
        justifyContent="center"
        sx={{
          transform: "translateY(-40px)",
        }}
      >
        <Grid container justifyContent="center" alignItems="center">
          <Grid item xs={11} sm={9} md={5} lg={4} xl={3}>
            <Card>
              <MKBox
                variant="gradient"
                bgColor="info"
                borderRadius="lg"
                coloredShadow="info"
                mx={2}
                mt={2}
                p={2}
                mb={1}
                textAlign="center"
              >
                <MKTypography variant="h4" fontWeight="medium" color="white" mt={1}>
                  Sign up
                </MKTypography>

                <MKTypography variant="body2" color="white" opacity={0.8} mt={1}>
                  Create your account
                </MKTypography>
              </MKBox>

              <MKBox pt={4} pb={3} px={3}>
                <MKBox component="form" role="form" onSubmit={handleSubmit}>
                  <MKBox mb={2}>
                    <MKInput
                      type="email"
                      label="Email"
                      name="email"
                      fullWidth
                      value={formData.email}
                      onChange={handleChange}
                    />
                  </MKBox>

                  <MKBox mb={2}>
                    <MKInput
                      type="password"
                      label="Password"
                      name="password"
                      fullWidth
                      value={formData.password}
                      onChange={handleChange}
                    />
                  </MKBox>

                  <MKBox mb={2}>
                    <MKInput
                      type="password"
                      label="Confirm Password"
                      name="confirmPassword"
                      fullWidth
                      value={formData.confirmPassword}
                      onChange={handleChange}
                    />
                  </MKBox>

                  <MKBox mt={4} mb={1}>
                    <MKButton
                      type="submit"
                      variant="gradient"
                      color="info"
                      fullWidth
                      sx={{
                        display: "flex",
                        justifyContent: "center",
                        alignItems: "center",
                        textTransform: "none",
                        fontWeight: "bold",
                      }}
                    >
                      Create Account
                    </MKButton>
                  </MKBox>

                  <MKBox mt={3} mb={1} textAlign="center">
                    <MKTypography variant="button" color="text">
                      Already have an account?{" "}
                      <MKTypography
                        component={Link}
                        to="/sign-in"
                        variant="button"
                        color="info"
                        fontWeight="medium"
                        textGradient
                      >
                        Sign in
                      </MKTypography>
                    </MKTypography>
                  </MKBox>
                </MKBox>
              </MKBox>
            </Card>
          </Grid>
        </Grid>
      </MKBox>

      <MKBox width="100%" position="absolute" zIndex={2} bottom="1.625rem">
        <SimpleFooter light />
      </MKBox>
    </>
  );
}

export default SignUp;
