export function generateThaiCitizenId(): string {
  const digits: string[] = Array.from({ length: 12 }, () =>
    Math.floor(Math.random() * 10).toString()
  );

  const weightedSum: number = digits.reduce(
    (acc: number, digit: string, index: number) =>
      acc + Number(digit) * (13 - index),
    0
  );

  let checksum: number = weightedSum % 11;
  checksum =
    checksum === 0 ? 1 :
    checksum === 1 ? 0 :
    11 - checksum;

  return digits.join("") + checksum.toString();
}