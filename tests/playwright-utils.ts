import { Page, Locator, expect } from '@playwright/test';

//############################## scrollUntilEnabled (App form) ############################################

export async function scrollUntilEnabled(
  page: Page,
  locator: Locator,
  options?: { maxScrolls?: number; scrollStep?: number; timeout?: number }
) {
  const { maxScrolls = 30, scrollStep = 400, timeout = 15_000 } = options ?? {};

  for (let i = 0; i < maxScrolls; i++) {
    if (await locator.isEnabled()) {
      return; // ✅ break when enabled
    }

    // Scroll approach 1 — wheel scroll
    await page.mouse.wheel(0, scrollStep);

    // Scroll approach 2 (optional) — scroll to locator if in view
    try {
      await locator.scrollIntoViewIfNeeded({ timeout: 2000 });
    } catch {}

    await page.waitForTimeout(300); // short breathing time between scrolls
  }

  // ถ้าครบ maxScrolls แล้วปุ่มยังไม่ enable → fail
  await expect(locator).toBeEnabled({ timeout });
}

export async function getLocatorText(locator: Locator, timeout = 10_000): Promise<string> {
  await locator.waitFor({ state: 'visible', timeout });
  return (await locator.textContent())?.trim() ?? '';
}

//############################## getPhoneNumberText ############################################
/**
 * ดึงเบอร์หลายตำแหน่งที่เป็น span แล้วรวมเป็น string "เบอร์ xxxx"
 */
export async function getPhoneNumberText(page: Page): Promise<string> {
  const selectors = [
    '.font-semibold > span',
    'span:nth-child(2) > span',
    'span:nth-child(3) > span',
    'span:nth-child(4) > span',
    'span:nth-child(5) > span',
    'span:nth-child(6) > span',
    'span:nth-child(7) > span',
    'span:nth-child(8) > span',
    'span:nth-child(9) > span',
    'span:nth-child(10) > span'
  ];

  let phone_num = '';

  for (const selector of selectors) {
    const el = page.locator(selector).first();
    const num = await getLocatorText(el);
    phone_num += num;
  }

  const phone_num_txt = `pick เบอร์ ${phone_num}`;
  return phone_num_txt; // ✅ return ค่าออกไปให้ file อื่นใช้ได้
}

export async function getDataLayerViewItem(page: Page): Promise<any | null> {
  return await page.evaluate(() => {
    const layer = (window as any).dataLayer ?? [];
    return layer.find((item: Record<string, any>) => item?.event === 'view_item')
  });
}

/*export async function waitForViewItemEvent(page: Page, timeout = 10_000): Promise<DataLayerItem | null> {
  const start = Date.now();

  while (Date.now() - start < timeout) {
    const result = await page.evaluate(() => {
      const layer: DataLayerItem[] = (window as any).dataLayer ?? [];
      const found = layer.find((item: DataLayerItem) =>
        item && (item.event === 'view_item' || item.event_name === 'view_item')
      );
      return found ?? null;
    });

    if (result) return result; // ✅ เจอแล้ว return ทันที

    await page.waitForTimeout(300); // เว้นช่วงก่อนเช็คอีกรอบ
  }

  return null; // ❌ ครบเวลาแล้วยังไม่เจอ
}*/


