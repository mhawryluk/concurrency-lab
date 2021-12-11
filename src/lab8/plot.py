import matplotlib.pyplot as plt

fig, ax = plt.subplots()

Ns = [5, 8, 10, 12, 15]

ax.plot(Ns, [26, 45, 55, 66, 81], label='wariant asymetryczny', color='darkblue', marker='o')
ax.plot(Ns, [75, 650, 2572, 10254, 81937], label='wariant z lokajem', color='darkgreen', marker='o')
ax.set_ylabel('suma czasu oczekiwania [ms]')
ax.set_xlabel('liczba filozofów')
plt.legend()
plt.show()
