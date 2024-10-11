document.addEventListener('DOMContentLoaded', () => {
    fetch('/timetable')
        .then(response => response.json())
        .then(data => {
            generateTimetable(data);
        })
        .catch(error => console.error('エラーが発生しました:', error));
});

function generateTimetable(timetableData) {
    const timetableBody = document.getElementById('timetableBody');
    const days = ['月', '火', '水', '木', '金'];
    const periods = 5; // 1日あたりの時限数（5時限）

    let schedule = Array.from({ length: days.length }, () => Array(periods).fill(null));

    timetableData.forEach(data => {
        let count = 0;
        while (count < data.numClasses) {
            let dayIndex = Math.floor(Math.random() * days.length);
            let periodIndex = Math.floor(Math.random() * periods);

            // まだ埋まっていないコマにデータを追加
            if (!schedule[dayIndex][periodIndex]) {
                schedule[dayIndex][periodIndex] = `${data.subject} (${data.room}) ${data.teacher}`;
                count++;
            }
        }
    });

    // 表に時間割を反映
    for (let period = 0; period < periods; period++) {
        const row = document.createElement('tr');
        const timeCell = document.createElement('td');
        timeCell.textContent = `${period + 1}限`;
        row.appendChild(timeCell);

        days.forEach((day, dayIndex) => {
            const cell = document.createElement('td');
            if (schedule[dayIndex][period]) {
                cell.textContent = schedule[dayIndex][period];
            }
            row.appendChild(cell);
        });

        timetableBody.appendChild(row);
    }
}
