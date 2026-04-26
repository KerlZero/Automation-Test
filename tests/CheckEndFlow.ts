import { Page } from '@playwright/test';

export async function CheckIDCardIncorrect(
  page: Page,
  popupSelector: string,
  logMessage = 'เลขบัตรประชาชนไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง',
  timeout = 5_000
): Promise<boolean> {
  const popup = page.getByText('เลขบัตรประชาชนไม่ถูกต้อง กรุณาลองใหม่อีกครั้ง')
  try {
    await popup.waitFor({ state: 'visible', timeout });
    console.log(logMessage);
    return true; // ✅ เจอ popup แล้วให้จบ flow
  } catch {
    return false; // ❌ ยังไม่เจอ popup
  }
}

export async function CheckErrorCart(
  page: Page,
  popupSelector: string,
  logMessage = 'ขออภัย ไม่สามารถทำรายการได้ 404',
  timeout = 5_000
): Promise<boolean> {
  const popup = page.getByText('ไม่สามารถทำรายการได้')
  try {
    await popup.waitFor({ state: 'visible', timeout });
    console.log(logMessage);
    return true; // ✅ เจอ popup แล้วให้จบ flow
  } catch {
    return false; // ❌ ยังไม่เจอ popup
  }
}