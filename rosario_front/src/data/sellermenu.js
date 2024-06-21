  const sellermenu = [
    {
      key: "/admin/dashboard",
      icon: "HomeOutlined",
      label: "DashBoard",
    },
    {
      key: "/admin/edit",
      icon: "EditOutlined",
      label: "Edit",
      children: [
        { key: "/admin/edit/catalog", label: "Catalog" },
        { key: "/admin/edit/profile", label: "Profile" },
        { key: "/admin/edit/product", label: "Product" },
      ],
    },
    {
      key: "/admin/sales",
      icon: "LineChartOutlined",
      label: "Sales",
      children: [
        { key: "/admin/sales/term", label: "Term" },
        { key: "/admin/sales/type", label: "Type" },
      ],
    },
    {
      key: "/admin/orders",
      icon: "CalendarOutlined",
      label: "Orders",
      children: [
        { key: "/admin/orders/suborders", label: "Sub Orders" },
        { key: "/admin/orders/genorders", label: "Gen Orders" },
      ],
    },
    {
      key: "/admin/subscription",
      icon: "TeamOutlined",
      label: "Subscription",
    },
  ];

  export default sellermenu;