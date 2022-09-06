export const dateFormat = (date) => {
    const week = {
      Sun: "일",
      Mon: "월",
      Tue: "화",
      Wed: "수",
      Thu: "목",
      Fri: "금",
      Sat: "토",
    };
    const month = {
      Jan: 1,
      Feb: 2,
      Mar: 3,
      Apr: 4,
      May: 5,
      Jun: 6,
      Jul: 7,
      Aug: 8,
      Sep: 9,
      Oct: 10,
      Nov: 11,
      Dec: 12,
    };
    const dateList = String(date).split(" ");
    return (
      dateList[3] +
      ". " +
      month[dateList[1]] +
      ". " +
      dateList[2] +
      "(" +
      week[dateList[0]] +
      ")"
    );
  };