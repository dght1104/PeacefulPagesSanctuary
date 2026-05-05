import Card from "@mui/material/Card";
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import MKButton from "components/MKButton";

function MKBookCard() {
  const book = {
    title: "Clean Code",
    author: "Robert C. Martin",
    price: 20,
    image: "https://images-na.ssl-images-amazon.com/images/I/41SH-SvWPxL.jpg",
  };

  return (
    <Card sx={{ height: "100%" }}>
      <MKBox
        component="img"
        src={book.image}
        alt={book.title}
        width="100%"
        height="250px"
        sx={{ objectFit: "cover" }}
      />

      <MKBox p={2}>
        <MKTypography variant="h6">{book.title}</MKTypography>

        <MKTypography variant="body2" color="text">
          {book.author}
        </MKTypography>

        <MKTypography variant="h6" color="info">
          ${book.price}
        </MKTypography>

        <MKButton color="info" fullWidth>
          Add to Cart
        </MKButton>
      </MKBox>
    </Card>
  );
}

export default MKBookCard;
