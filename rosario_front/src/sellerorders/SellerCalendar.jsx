import React from "react";
import { Calendar, momentLocalizer } from "react-big-calendar";
import moment from "moment";
import "react-big-calendar/lib/css/react-big-calendar.css";
// import "./SellerCalendar.module.css";

const localizer = momentLocalizer(moment);

const MyCalendar = () => {
  const myEventsList = [
    {
      title: "Meeting",
      start: new Date(),
      end: new Date(),
    },
  ];

  return (
    <div style={{ height: 500 }}>
      <Calendar
        localizer={localizer}
        events={myEventsList}
        startAccessor="start"
        endAccessor="end"
        style={{ height: "100%" }}
      />
    </div>
  );
};

export default MyCalendar;
