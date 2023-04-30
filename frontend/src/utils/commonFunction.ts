// interface, type area
interface cssObj {
  key: keyof CSSStyleDeclaration | string;
  cssValue: string;
}

// function area
// ------------------------------------------------------------

/**
 * set targetElement.style[key] = value
 * @param $targetElement target HTMLElement
 * @param key css key
 * @param value css value
 */
const changeCSS = (
  $targetElement: HTMLElement | SVGSVGElement,
  key: keyof CSSStyleDeclaration | string,
  value: string
): void => {
  if (key in $targetElement.style) {
    $targetElement.style.setProperty(String(key), value);
  }
};

/**
 * set targetElement.style[key] = value multi times
 * @param $targetElement target HTMLElement
 * @param cssArray css style Obj array
 */
const changeMultiCSS = (
  $targetElement: HTMLElement,
  cssArray: cssObj[]
): void => {
  cssArray.forEach(({ key, cssValue }) => {
    if (key in $targetElement.style) {
      $targetElement.style.setProperty(String(key), cssValue);
    }
  });
};

/**
 * set targetElement's eventHandler
 * @param $target target HTMLElement
 * @param eventHandler event callback
 * @param eventType click, mousedown, ... and so on
 */
const eventTo = (
  $target: HTMLElement,
  eventHandler: (() => void) | ((event:Event) => void),
  eventType = "click"
) => {
  const validateEventTypes = ["click"];

  if (!validateEventTypes.includes(eventType)) {
    throw new Error(`[Error] Invalid event type: ${eventType}`);
  }
  $target.addEventListener(eventType, eventHandler);
};

/**
 * shortcut for document.querySelector
 * @param cssSelector css selector
 * @param root starting node (default value is document.body)
 * @returns corresponding HTML element
 */
function $(cssSelector: string, root = document.body): HTMLElement | null {
  if (root.querySelector(cssSelector)) {
    return root.querySelector(cssSelector);
  }
  return null;
}

const createElement = (tagName:string):HTMLElement => document.createElement(tagName);

const createMultiElements = (tagNames: string[]):HTMLElement[] => {
  const $elements:HTMLElement[] = [];
  tagNames.forEach((tagName) => {
    $elements.push(createElement(tagName));
  })
  return $elements;
}

const formatKoreanCurrency = (num:number | string): string => {
  const money = num.toString();

  if (money.length <= 4) {
    return money;
  }

  const moneyArray = [];
  for (let i = money.length; i > 0; i -= 3) {
    const start = Math.max(i - 3, 0);
    const end = i;
    const slicedNumber = money.slice(start, end);
    moneyArray.unshift(slicedNumber);
  }

  const koreaMoney = moneyArray.join(",");
  return koreaMoney;
}

export {
  changeCSS,
  changeMultiCSS,
  eventTo,
  $,
  createElement,
  createMultiElements,
  formatKoreanCurrency,
};
export type { cssObj };
