/**
=========================================================
* Material Kit 2 React - v2.1.0
=========================================================
*/

// Pages
import AboutUs from "layouts/pages/landing-pages/about-us";
import ContactUs from "layouts/pages/landing-pages/contact-us";
import SignIn from "layouts/pages/authentication/sign-in";
import SignUp from "layouts/pages/authentication/sign-up";
import Presentation from "layouts/pages/presentation";
import Product from "layouts/pages/product";
import ProductDetailPage from "layouts/pages/productdetail";
import Profile from "pages/LandingPages/Profile";
import Cart from "pages/LandingPages/Cart";
import Checkout from "pages/LandingPages/Checkout";
import Orders from "pages/LandingPages/Orders";
import OrderDetail from "pages/LandingPages/OrderDetail";
const routes = [
  {
    name: "Home",
    route: "/",
    component: <Presentation />,
  },

  // {
  //   name: "Books",
  //   collapse: [
  //     {
  //       name: "Categories",
  //       collapse: [
  //         { name: "Science", route: "/books/science", component: <Product /> },
  //         { name: "Novels", route: "/books/novels" },
  //         { name: "Business", route: "/books/business" },
  //       ],
  //     },
  //     {
  //       name: "Browse",
  //       collapse: [
  //         { name: "Best Sellers", route: "/books/best-sellers" },
  //         { name: "New Arrivals", route: "/books/new" },
  //         { name: "On Sale", route: "/books/sale" },
  //       ],
  //     },
  //   ],
  // },
  {
    name: "Product",
    route: "/product",
    component: <Product />,
  },
  {
    name: "Product Detail",
    route: "/product/:id",
    component: <ProductDetailPage />,
    noNavbar: true,
  },

  {
    name: "About Us",
    route: "/about-us",
    component: <AboutUs />,
  },
  {
    name: "Contact Us",
    route: "/contact-us",
    component: <ContactUs />,
  },

  {
    name: "Cart",
    route: "/cart",
    component: <Cart />,
  },
  {
    name: "Checkout",
    route: "/checkout",
    component: <Checkout />,
  },

  {
    name: "Account",
    collapse: [
      {
        name: "Sign In",
        route: "/sign-in",
        component: <SignIn />,
      },

      {
        name: "Sign Up",
        route: "/sign-up",
        component: <SignUp />,
      },
      {
        name: "Order Detail",
        route: "/order-detail/:id",
        component: <OrderDetail />,
      },
      {
        name: "Orders",
        route: "/orders",
        component: <Orders />,
      },

      {
        name: "Profile",
        route: "/profile",
        component: <Profile />,
      },
    ],
  },
];

export default routes;
