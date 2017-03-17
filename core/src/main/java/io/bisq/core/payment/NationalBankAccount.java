/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.core.payment;

import io.bisq.common.app.Version;
import io.bisq.wire.payload.payment.BankAccountContractData;
import io.bisq.wire.payload.payment.NationalBankAccountContractData;
import io.bisq.wire.payload.payment.PaymentAccountContractData;
import io.bisq.wire.payload.payment.PaymentMethod;

public final class NationalBankAccount extends CountryBasedPaymentAccount implements SameCountryRestrictedBankAccount {
    // That object is saved to disc. We need to take care of changes to not break deserialization.
    private static final long serialVersionUID = Version.LOCAL_DB_VERSION;

    public NationalBankAccount() {
        super(PaymentMethod.NATIONAL_BANK);
    }

    @Override
    protected PaymentAccountContractData setContractData() {
        return new NationalBankAccountContractData(paymentMethod.getId(), id, paymentMethod.getMaxTradePeriod());
    }

    @Override
    public String getBankId() {
        return ((BankAccountContractData) contractData).getBankId();
    }

    @Override
    public String getCountryCode() {
        return getCountry() != null ? getCountry().code : "";
    }
}