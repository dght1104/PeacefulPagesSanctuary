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
    name: "Discover",
    collapse: [
      { name: "Recommended", route: "/recommend" },
      { name: "Authors", route: "/authors" },
    ],
  },

  {
    name: "Info",
    collapse: [
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
    ],
  },

  {
    name: "Cart",
    route: "/cart",
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
        name: "Orders",
        route: "/orders",
      },

      {
        name: "Profile",
        route: "/profile",
      },
    ],
  },
];

export default routes;
