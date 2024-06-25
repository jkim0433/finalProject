import React from 'react';
import { Disclosure, DisclosureButton, DisclosurePanel } from '@headlessui/react';
import { Bars3Icon, XMarkIcon } from '@heroicons/react/24/outline';
import { navigation } from "../data/menu";
import classNames from 'classnames';

const MobileMenuButton = () => {
  return (
    <Disclosure>
      {({ open }) => (
        <>
          {/* Mobile menu button */}
          <DisclosureButton
            className="relative inline-flex items-center justify-center rounded-md bg-gray-800 p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800"
          >
            <span className="absolute -inset-0.5" />
            <span className="sr-only">Open main menu</span>
            {open ? (
              <XMarkIcon className="block h-6 w-6" aria-hidden="true" />
            ) : (
              <Bars3Icon className="block h-6 w-6" aria-hidden="true" />
            )}
          </DisclosureButton>

          {/* Mobile menu panel */}
          <DisclosurePanel className="md:hidden absolute top-0 left-0 w-full bg-white shadow-lg rounded-lg z-10 mt-3">
            <div className="p-4 space-y-4">
              {/* Navigation links */}
              {navigation.map((item) => (
                <a
                  key={item.name}
                  href={item.href}
                  className={classNames(
                    item.current ? 'bg-gray-900 text-white' : 'text-gray-900 hover:bg-gray-100',
                    'block rounded-md px-3 py-2 text-base font-medium',
                  )}
                  aria-current={item.current ? 'page' : undefined}
                >
                  {item.name}
                </a>
              ))}
            </div>
          </DisclosurePanel>
        </>
      )}
    </Disclosure>
  );
};

export default MobileMenuButton;
