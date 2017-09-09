package re.gaspa.bcmanager.ui.activities;

import android.app.ActionBar;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import re.gaspa.bcmanager.R;
import re.gaspa.bcmanager.databinding.ActivityBusinesscardBinding;
import re.gaspa.bcmanager.ui.models.BusinessCard;
import re.gaspa.bcmanager.utils.Utils;

public class BusinessCardActivity extends AppCompatActivity implements View.OnClickListener {

    private BusinessCard businessCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityBusinesscardBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_businesscard);

        businessCard = getIntent().getExtras().getParcelable("businesscard");

        if (businessCard == null) return;

        try {
            int color = Color.parseColor(businessCard.getColore());
            binding.iconPlace.setImageTintList(ColorStateList.valueOf(color));
            binding.iconCall.setImageTintList(ColorStateList.valueOf(color));
            binding.iconMail.setImageTintList(ColorStateList.valueOf(color));
            binding.iconTelegram.setImageTintList(ColorStateList.valueOf(color));
            binding.iconWebsite.setImageTintList(ColorStateList.valueOf(color));
            binding.iconWork.setImageTintList(ColorStateList.valueOf(color));
            binding.imageProfile.setBorderColor(color);

            ActionBar bar = getActionBar();
            bar.setBackgroundDrawable(new ColorDrawable(color));
        } catch (Exception e) {

        }

        Bitmap profile_image = businessCard.getProfilo();
        Bitmap background_image = businessCard.getSfondo();

        if (profile_image != null) binding.imageProfile.setImageBitmap(profile_image);
        if (background_image != null) binding.backgroundImage.setImageBitmap(background_image);

        Integer id = businessCard.getId();
        Boolean preferito = businessCard.getPreferito();
        String nome = businessCard.getNome();
        String telefono = businessCard.getTelefono();
        String email = businessCard.getEmail();
        String ruolo = businessCard.getLavoroRuolo();
        String luogo = businessCard.getLavoroLuogo();
        Location luogoCoordinate = businessCard.getLavoroCoordinate();
        String citta = businessCard.getCasaCitta();
        String strada = businessCard.getCasaStrada();
        Location cittaCoordinate = businessCard.getCasaCoordinate();
        String sito = businessCard.getSito();
        String telegram = businessCard.getTelegram();

        if (nome != null && nome.length() > 0) {
            binding.textName.setText(nome);
        }

        // Abitazione
        if (citta != null && citta.length() > 0) {
            binding.textCity.setText(citta);
            if (strada != null && strada.length() > 0)
                binding.textStreet.setText(strada);
            else
                binding.textStreet.setVisibility(View.GONE);
            binding.cardCity.setOnClickListener(this);

        } else
            binding.cardCity.setVisibility(View.GONE);

        // Luogo di lavoro
        if (ruolo != null && ruolo.length() > 0) {
            binding.textRole.setText(ruolo);
            if (luogo != null && luogo.length() > 0)
                binding.textJobplace.setText(luogo);
            else
                binding.textJobplace.setVisibility(View.GONE);
            binding.cardWork.setOnClickListener(this);
        } else
            binding.cardWork.setVisibility(View.GONE);

        // Numero di telefono
        if (telefono != null && telefono.length() > 0) {
            binding.textNumber.setText(telefono);
            binding.cardNumber.setOnClickListener(this);
        } else
            binding.cardNumber.setVisibility(View.GONE);

        // Email
        if (email != null && email.length() > 0) {
            binding.textMail.setText(email);
            binding.cardMail.setOnClickListener(this);
        } else
            binding.cardMail.setVisibility(View.GONE);

        // Telegram
        if (telegram != null && telegram.length() > 0) {
            binding.textTelegram.setText(telegram);
            binding.cardTelegram.setOnClickListener(this);
        } else
            binding.cardTelegram.setVisibility(View.GONE);

        // Sito web
        if (sito != null && sito.length() > 0) {
            binding.textWebsite.setText(sito);
            binding.cardWebsite.setOnClickListener(this);
        } else
            binding.cardWebsite.setVisibility(View.GONE);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favourite, menu);

        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.card_city:
                Location city = businessCard.getCasaCoordinate();
                if (city != null) {
                    Utils.openLocation(city, view.getContext());
                }
                break;

            case R.id.card_mail:
                String mail = businessCard.getEmail();
                if (mail != null && mail.length() > 0) {
                    Utils.openMail(mail, view.getContext());
                }
                break;

            case R.id.card_number:
                String number = businessCard.getTelefono();
                if (number != null && number.length() > 0) {
                    Utils.openNumber(number, view.getContext());
                }
                break;

            case R.id.card_telegram:
                String nick = businessCard.getTelegram();
                if (nick != null && nick.length() > 0) {
                    Utils.openTelegram(nick, view.getContext());
                }
                break;

            case R.id.card_website:
                String website = businessCard.getSito();
                if (website != null && website.length() > 0) {
                    Utils.openWebsite(website, view.getContext());
                }
                break;

            case R.id.card_work:
                Location jCity = businessCard.getLavoroCoordinate();
                if (jCity != null) {
                    Utils.openLocation(jCity, view.getContext());
                }
                break;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_favourite) {
            item.setChecked(!item.isChecked());
            // TODO Filter
        }

        return super.onOptionsItemSelected(item);
    }

}
