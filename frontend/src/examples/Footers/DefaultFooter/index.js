// react-router-dom components
import { Link } from "react-router-dom";

// prop-types
import PropTypes from "prop-types";

// @mui material components
import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";

// Material Kit components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";

function DefaultFooter({ content }) {
  const { brand, socials, menus, copyright } = content;

  return (
    <MKBox component="footer" sx={{ py: 1 }}>
      <Container>
        <Grid container justifyContent="center" alignItems="flex-start" textAlign="center">
          {/* ===== BRAND ===== */}
          <Grid item xs={12} md={3}>
            <MKBox>
              <Link to={brand.route}>
                <MKBox
                  component="img"
                  src={brand.image}
                  alt={brand.name}
                  sx={{
                    width: 50,
                    mb: 1,
                    mx: "auto",
                    display: "block",
                  }}
                />
              </Link>

              <MKTypography variant="h6" fontWeight="bold">
                {brand.name}
              </MKTypography>
            </MKBox>

            {/* Social */}
            <MKBox display="flex" justifyContent="center" mt={2}>
              {socials.map(({ icon, link }, key) => (
                <MKTypography
                  key={link}
                  component="a"
                  href={link}
                  target="_blank"
                  rel="noreferrer"
                  variant="h5"
                  color="dark"
                  opacity={0.8}
                  mr={key !== socials.length - 1 ? 2 : 0}
                >
                  {icon}
                </MKTypography>
              ))}
            </MKBox>
          </Grid>

          {/* ===== MENUS ===== */}
          {menus.map(({ name: title, items }) => (
            <Grid key={title} item xs={6} md={2}>
              <MKTypography variant="button" fontWeight="bold" textTransform="capitalize" mb={1}>
                {title}
              </MKTypography>

              <MKBox component="ul" sx={{ listStyle: "none", p: 0, m: 0 }}>
                {items.map(({ name, route, href }) => (
                  <MKBox key={name} component="li" sx={{ mb: 0.5 }}>
                    {href ? (
                      <MKTypography
                        component="a"
                        href={href}
                        target="_blank"
                        rel="noreferrer"
                        variant="button"
                        color="text"
                      >
                        {name}
                      </MKTypography>
                    ) : (
                      <MKTypography component={Link} to={route} variant="button" color="text">
                        {name}
                      </MKTypography>
                    )}
                  </MKBox>
                ))}
              </MKBox>
            </Grid>
          ))}

          {/* ===== COPYRIGHT ===== */}
          <Grid item xs={12}>
            <MKBox mt={4}>
              <MKTypography variant="body2" color="text">
                {copyright}
              </MKTypography>
            </MKBox>
          </Grid>
        </Grid>
      </Container>
    </MKBox>
  );
}
DefaultFooter.propTypes = {
  content: PropTypes.object.isRequired,
};

export default DefaultFooter;
